import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import { resolve } from 'path';

export default defineConfig({
  plugins: [react()],
  root: '.',
  server: {
    port: 5173,
    proxy: {
      '/ws': {
        target: 'http://localhost:5000',
        ws: true,
        changeOrigin: true
      }
    }
  },
  build: {
    outDir: resolve(__dirname, '../../src/main/resources/static/assets'),
    emptyOutDir: false,
    sourcemap: false,
    rollupOptions: {
      input: {
        chat: resolve(__dirname, 'index.html')
      },
      output: {
        entryFileNames: 'chat-[hash].js',
        chunkFileNames: 'chat-[hash].js',
        assetFileNames: (assetInfo) => {
          if (assetInfo.name && assetInfo.name.endsWith('.css')) {
            return 'chat-[hash][extname]';
          }

          return '[name]-[hash][extname]';
        }
      }
    }
  }
});
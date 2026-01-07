const fs = require('fs');
const path = require('path');

const staticDir = path.resolve(__dirname, '..', '..', '..', 'src', 'main', 'resources', 'static');
const srcIndex = path.join(staticDir, 'index.html');
const dest = path.join(staticDir, 'chat.html');

try {
  if (!fs.existsSync(srcIndex)) {
    console.warn(`postbuild: ${srcIndex} not found — build may have failed.`);
    process.exit(0);
  }
  fs.copyFileSync(srcIndex, dest);
  console.log(`postbuild: copied index.html -> chat.html`);

  const assetsDir = path.join(staticDir, 'assets');
  if (fs.existsSync(assetsDir)) {
    const files = fs.readdirSync(assetsDir);
    const renameMap = {};
    for (const f of files) {
      const m = f.match(/^(index(-[A-Za-z0-9_.]+))(\.(js|css|map))$/);
      if (m) {
        const newName = f.replace(/^index/, 'chat');
        try {
          fs.renameSync(path.join(assetsDir, f), path.join(assetsDir, newName));
          renameMap[f] = newName;
          console.log(`postbuild: renamed ${f} -> ${newName}`);
        } catch (e) {
          console.warn('postbuild: failed to rename', f, e.message);
        }
      }
    }

    if (Object.keys(renameMap).length) {
      const updateFileRefs = (htmlPath) => {
        try {
          let txt = fs.readFileSync(htmlPath, 'utf8');
          for (const [oldName, newName] of Object.entries(renameMap)) {
            const oldRef = `/assets/${oldName}`;
            const newRef = `/assets/${newName}`;
            txt = txt.split(oldRef).join(newRef);
          }
          fs.writeFileSync(htmlPath, txt, 'utf8');
          console.log(`postbuild: updated asset refs in ${path.basename(htmlPath)}`);
        } catch (e) { console.warn('postbuild: failed to update refs in', htmlPath, e.message); }
      };

      updateFileRefs(srcIndex);
      updateFileRefs(dest);
    }
  }
} catch (err) {
  console.error('postbuild error', err);
  process.exit(1);
}
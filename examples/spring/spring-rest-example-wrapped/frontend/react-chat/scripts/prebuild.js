const fs = require('fs');
const path = require('path');

const assetsDir = path.resolve(__dirname, '..', '..', '..', 'src', 'main', 'resources', 'static', 'assets');

function safeRmDir(dir) {
  try {
    if (!fs.existsSync(dir)) return console.log(`prebuild: ${dir} does not exist, nothing to clean`);
    const entries = fs.readdirSync(dir);
    if (entries.length === 0) return console.log(`prebuild: ${dir} is already empty`);

    const removed = [];
    for (const name of entries) {
      if (/^index[-A-Za-z0-9_.]+\.(js|css|map)$/.test(name)) {
        const fp = path.join(dir, name);
        try { fs.unlinkSync(fp); removed.push(name); } catch (e) { console.warn('prebuild: failed to remove', fp, e.message); }
      }
    }

    if (removed.length) console.log('prebuild: removed:', removed.join(', '));
    else console.log('prebuild: no matching index-* assets to remove');
  } catch (err) {
    console.error('prebuild: error cleaning assets dir', dir, err);
    process.exit(1);
  }
}

safeRmDir(assetsDir);

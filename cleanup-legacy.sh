#!/bin/bash
# Legacy AngularJS Cleanup Script
# Run this script to remove all old files and keep only the modern Svelte 5 app

echo "🗂️ Removing Legacy AngularJS Files..."

# Remove main AngularJS application folder
rm -rf app/

# Remove old build system files
rm -f bower.json
rm -f Gruntfile.js

# Remove old testing setup
rm -rf test/

# Remove intermediate migration files (optional - keep if you want history)
rm -f package-modern.json
rm -f package-svelte.json

# Remove old documentation (optional - keep if you want migration history)
# rm -f MIGRATION_GUIDE.md
# rm -f DEPRECATION_MIGRATION.md
# rm -f SKELETON_MIGRATION_PLAN.md
# rm -f SKELETON_MIGRATION_COMPLETE.md
# rm -f SVELTE5_MIGRATION_COMPLETE.md
# rm -f UPGRADE_NOTES.md

echo "✅ Cleanup complete! Your project now contains only:"
echo "   - src/ (Modern Svelte 5 application)"
echo "   - Modern build configuration files"
echo "   - Migration documentation (optional)"

echo ""
echo "🚀 Next steps:"
echo "   npm run dev    # Start your Svelte 5 app"
echo "   npm run build  # Build for production"

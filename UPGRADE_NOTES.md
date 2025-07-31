# Dependency Upgrade Notes

## Overview
This project has been updated from very outdated 2015 dependencies to modern versions. The major changes include:

### Package.json Updates
- **Node.js**: Updated minimum requirement from 0.10.0 to 14.0.0
- **Grunt**: Updated from 0.4.1 to 1.6.1
- **All Grunt plugins**: Updated to latest compatible versions
- **Karma**: Updated from 0.12.31 to 6.4.2
- **Jasmine**: Updated from 2.2.0 to 4.6.0
- **Added Chrome launcher**: For better testing alternatives to PhantomJS

### Bower.json Updates
- **AngularJS**: Updated from 1.2.16 to 1.8.3 (latest 1.x)
- **Angular Material**: Updated from 0.8.3 to 1.2.5
- **jQuery**: Updated from 1.11.1 to 3.7.1
- **Bootstrap**: Updated to 3.4.3
- **All Angular modules**: Aligned to version 1.8.3

### Key Changes Made
1. **grunt-ngmin** replaced with **grunt-ng-annotate** (ngmin is deprecated)
2. **phantomjs-prebuilt** added as dependency
3. **karma-chrome-launcher** added for modern testing
4. **Resolution conflicts** handled in bower.json
5. **Karma configuration** updated to include all Angular modules

## Installation Steps

1. **Clean old dependencies:**
   ```bash
   rm -rf node_modules bower_components
   ```

2. **Install npm dependencies:**
   ```bash
   npm install
   ```

3. **Install bower dependencies:**
   ```bash
   bower install
   ```

4. **Run the project:**
   ```bash
   npm run serve
   # or
   grunt serve
   ```

## Breaking Changes to Watch For

### AngularJS 1.2.16 → 1.8.3
- Some deprecated features may need updates
- Check for any custom directives or services that might be affected
- Animation syntax may need minor adjustments

### Angular Material 0.8.3 → 1.2.5
- API changes in Material components
- Some component names or attributes may have changed
- Check all md-* directives for compatibility

### Grunt Plugin Updates
- Some configuration options may have changed
- Build process should remain largely the same
- Check Gruntfile.js if any tasks fail

## Testing Updates
- Karma configuration now includes all Angular modules
- PhantomJS still default but Chrome launcher available
- To use Chrome for testing, change `browsers: ['Chrome']` in karma.conf.js

## Recommendations
1. Test thoroughly after upgrade
2. Consider migrating to modern build tools (Webpack, Vite) for future updates
3. Consider upgrading to Angular 2+ for long-term maintainability
4. Add package-lock.json and .npmrc for better dependency management

## Troubleshooting
If you encounter issues:
1. Clear node_modules and bower_components
2. Reinstall dependencies
3. Check for any custom Grunt tasks that may need updates
4. Verify all paths in Gruntfile.js are correct

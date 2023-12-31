/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

/** @type {import('@docusaurus/plugin-content-docs').SidebarsConfig} */
const sidebars = {
  docs: [
    'introduction',
    {
      type: 'category',
      label: 'Guides',
      link: {
        type: 'doc',
        id: 'guides/documentation-style',
      },
      items: [
        'guides/toplevel-property',
        'guides/class',
        'guides/typealias',
        'guides/composable-function',
        'guides/normal-function',
        'guides/color',
        'guides/typography',
        'guides/icon',
      ],
    },
    'development',
    'architecture',
    'modularization',
    'testing',
    'contributing',
    'license',
  ],
};
module.exports = sidebars;


def call() {
  checkout([
    $class: 'GitSCM',
    branches: scm.branches,
    doGenerateSubmoduleConfigurations: false,
    extensions: [[
      $class: 'CloneOption',
      noTags: false,
      shallow: false,
      depth: 0,
      reference: ''
      ]],
    userRemoteConfigs: scm.userRemoteConfigs,
  ])
}
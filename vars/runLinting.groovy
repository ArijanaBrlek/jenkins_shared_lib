
def call(lintDirectories){
    sh "pylint --output-format=parseable ${joinArray(lintDirectories)} > pylint.log || exit 0"
    sh 'cat pylint.log'

    step([
        $class: 'WarningsPublisher',
        parserConfigurations: [[
            parserName: 'PYLint',
            pattern   : 'pylint.log'
        ]],
        healthy: '0',
        canResolveRelativePaths: true,
        usePreviousBuildAsReference: true
    ])
}

@NonCPS
def joinArray(lintDirectories){
  return lintDirectories.join(' ')
}
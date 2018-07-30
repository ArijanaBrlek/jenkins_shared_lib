def call(Map pipelineParams) {
    node('master') {
        ws("/var/jenkins_home/workspace/testing_jenkinsfile")
        {
            stage('Git checkout') {
                checkoutGitRepository()
            }

            stage('Lint') {
                docker.image(pipelineParams.buildImage).inside('-u root'){
                    runLinting(pipelineParams.lintDirectories)
                }
            }

            stage('Build'){
                docker.image(pipelineParams.buildImage).inside('-u root'){
                    echo 'Build.'
                    def pylintGrade = sh(
                        script: 'tail -2 pylint.log',
                        returnStdout: true                        
                    ).trim()

                    echo "Pylint score\n: ${pylintGrade}"
                }
            }
        }
    }
}

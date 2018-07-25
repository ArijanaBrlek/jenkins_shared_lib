def call(Map pipelineParams) {
    node('master') {
        ws("/var/jenkins_home/workspace/testing_jenkinsfile")
        {
            stage('Git checkout') {
                checkoutGitRepository()
            }

            stage('Lint') {
                docker.image(pipelineParams.buildImage).inside('-u root'){
                    runLinting(pipelineParams.lintDirectory)
                }
            }

            stage('Build'){
                docker.image(pipelineParams.buildImage).inside('-u root'){
                    echo 'Build.'
                }
            }
        }
    }
}
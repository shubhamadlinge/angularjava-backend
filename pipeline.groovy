pipeline {
    agent any 
    stages {
        stage('code-pull'){
            steps {
                git branch: 'dev', url: 'https://github.com/mayurmwagh/project-backend.git'
            }
        }
        stage('code-Build'){
            steps {
               sh 'mvn clean package'
            }
        }
         stage('Deploy-K8s'){
            steps {
               sh '''
                    docker build . -t mayurwagh/project-backend-img:latest
                    docker push mayurwagh/project-backend-img:latest
                    docker rmi mayurwagh/project-backend-img:latest
                    kubectl apply -f ./deploy/

               '''
            }
        }
    }
}
pipeline {
    agent any 
    stages {
        stage('code-pull'){
            steps {
               git branch: 'dev', url: 'https://github.com/shubhamadlinge/angularjava-backend.git'
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
                    docker build . -t shubhamdocker3/project-backend-img:latest
                    docker push shubhamdocker3/project-backend-img:latest
                    docker rmi shubhamdocker3/project-backend-img:latest
                    kubectl apply -f ./deploy/

               '''
            }
        }
    }
}

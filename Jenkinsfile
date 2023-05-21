pipeline {
  agent any
  stages {
    stage('Log Tool Version') {
      parallel {
        stage('Log Tool Version') {
          steps {
            sh '''mvn --version
git --version
java -version'''
          }
        }

        stage('Check for POM') {
          steps {
            fileExists 'pom.xml'
          }
        }

      }
    }

    stage('Build with Maven') {
      steps {
        sh 'mvn clean package -Dmaven.test.skip'
      }
    }

    stage('Post Build Steps') {
      steps {
        writeFile(file: 'status.txt', text: '�?')
      }
    }

  }
}
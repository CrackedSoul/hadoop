pipeline {
    agent none
    stages {
        stage('Package') {
            agent {
                    docker {
                        image 'maven:3-alpine'
                        args '-v /usr/share/maven/ref:/root/.m2 -v /root/.sonar/cache:/root/.sonar/cache -v /home/docker/mavensettings/settings-bigdata.xml:/usr/share/maven/conf/settings.xml'
                    }
            }
            steps {
                echo 'Package..'
                sh 'mvn clean install'
            }
        } 
    }
    post{
    	failure{
	    	emailext (
	    		body: """
	    			${JOB_NAME}- Build #${BUILD_NUMBER} Result!</br>
	    			BUILD_URL: <a href=\"${env.BUILD_URL}\">${env.BUILD_URL}</a></br>
	    			JOB_URL:<a href=\"$JOB_URL\">$JOB_URL</br>
	    			LogInfo: <a href=\"${env.BUILD_URL}console\">${env.BUILD_URL}console</br>
	    		""", 
	    		recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], 
	    		subject: '${JOB_NAME}- Build #${BUILD_NUMBER} Construction Result',   
	    		to: 'huangyulong@mastercom.cn'
				)
    	}
    }
}
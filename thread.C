int rc1 = fork()
int rc2 = fork()
if (rc1 == 0 ){
    pthread_create();
    int rc3 = fork();
    pthread_create();
    if ( rc3 > 0 ){
        fork();
    }
}

if (rc2 > 0 ){
    int rc4 = fork();
    pthread_create();
    int rc5 = fork();
    pthread_create();
    if ( rc4 > 0 ){
        int rc6 = fork();
        pthread_create();
    }
}
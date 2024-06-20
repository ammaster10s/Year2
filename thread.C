#include <stdio.h>

int main()

{
    double epsilon = 1;
    float one = 1;

    while (1 + epsilon != 1){
        printf("Epsilon = %.100f\n", epsilon);
        epsilon/=2;

    }
    printf("Epsilon = %.100f\n", epsilon);

    if (1 + epsilon == 1)
        printf("yes\n");
    else
        printf("no");

    return 0;
}
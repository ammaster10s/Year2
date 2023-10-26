#include <stdio.h>

void box(int w, int h, char c)
{
    for (int i = 0; i < h; i++)
    {
        for (int j = 0; j < w; j++)
        {
            if (i == 0 || i == h - 1 || j == 0 || j == w - 1)
            {
                putchar(c);
            }
            else
            {
                putchar(' ');
            }
        }
        putchar('\n');
    }
}

int main()
{
    int width, height;
    char character;

    printf("Enter the width of the box: ");
    scanf("%d", &width);

    printf("Enter the height of the box: ");
    scanf("%d", &height);

    printf("Enter the character for the box: ");
    scanf(" %c", &character);

    box(width, height, character);
    return 0;
}
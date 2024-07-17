#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

char *onlylettersK(FILE *kf)
{
    int i = 0;
    char *str = malloc(sizeof(char) * 513);
    char ch;
    while (fscanf(kf, "%c", &ch) != EOF)
    {
        if (i < 512)
        {
            if (isalpha(ch))
            {
                str[i] = tolower(ch);
                i++;
            }
        }
    }

    str[i] = '\0';

    return str;
}

char *onlylettersP(FILE *pf)
{
    int i = 0;
    char *str2 = malloc(sizeof(char) * 513);
    char ch;
    while (fscanf(pf, "%c", &ch) != EOF)
    {
        if (i < 512)
        {
            if (isalpha(ch))
            {
                str2[i] = tolower(ch);
                i++;
            }
        }
    }

    str2[i] = '\0';

    int j = strlen(str2);

// pads the rest of the plaintext file with the character 'x'
    for (int k = j; k < 512; k++)
    {
        str2[k] = 'x';
    }

    return str2;
}

char *vCipher(char *key, char *plain)
{
    int keylen = strlen(key);
    int plainlen = strlen(plain);
    int i = 0;
    char *vcipher = malloc(sizeof(char) * 513);
    int j;

    while (i < plainlen)
    {
// logic for the cipher
        j = (((plain[i] - 'a') + (key[i % keylen] - 'a')) % 26) + 'a';
        vcipher[i] = j;
        i++;
    }

    vcipher[512] = '\0';

    return vcipher;
}

void forestfire(char *str)
{
    if (str == NULL)
    {
        return;
    }

    free(str);
}


int main(int argc, char** argv)
{
    FILE *pf;
    FILE *kf;
    int i;
    int b;

// checks to see if a null file is passed
    if ((kf = fopen(argv[1], "r")) == NULL)
    {
        fprintf(stderr, "Could not open %s in main()!\n", argv[1]);
        exit(0);
    }

    if ((pf = fopen(argv[2], "r")) == NULL)
    {
        fprintf(stderr, "Could not open %s in main()!\n", argv[2]);
        exit(0);
    }

// removes all non-alphabetical characters for the keyfile
    char *kstr = onlylettersK(kf);
    int a = strlen(kstr);

    printf("\n");
    printf("Key File:\n");

    for (i = 0; i < a; )
    {
        if (i % 80 == 0)
        {
            printf("\n");
        }
        printf("%c", kstr[i]);

        i++;
    }

// removes all non-alphabetical characters for the plaintext file
    char *pstr = onlylettersP(pf);
    b = strlen(pstr);

    printf("\n");
    printf("\n");

    printf("Plaintext File:\n");

    for (i = 0; i < b; )
    {
        if (i % 80 == 0)
        {
            printf("\n");
        }
        printf("%c", pstr[i]);

        i++;
    }

    printf("\n");
    printf("\n");

// performs the vigenere cipher using the keyfile and plaintext file
    char *cstr = vCipher(kstr, pstr);
    int d = strlen(cstr);

    printf("Ciphertext:\n");

    for (i = 0; i < d; )
    {
        if (i % 80 == 0)
        {
            printf("\n");
        }
        printf("%c", cstr[i]);

        i++;
    }

    printf("\n");
    printf("\n");


// calling forestfire function to free all dynamically allocated memory
    forestfire(kstr);
    forestfire(pstr);
    forestfire(cstr);

    return 0;

}

def caesar_word(s,n):
    ''' shifts string "s" by "n" '''

    alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
    result = ''

    for ch in s.lower():
        if (ch.isalpha()):
            result += alphabet[(alphabet.index(ch)+n)%26]
        else:
            result += ch
    return result

def caesar(s,n):
    result = ''
    for word in s.split(' '):
        result += caesar_word(word,n) + ' '
    return result


def main():
    x = 'UZCCA RWJCFQS PZCGGCA AWRRZS'
    for i in range(26):
        print(caesar(x,i))

if __name__ == '__main__':
    main()
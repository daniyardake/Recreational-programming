# This is a program that will help you to win in "Bool and Cow game"
# Enter the number you asked and his/her response and computer will delete
# all imposible numbers from list of "potential" numbers

def is_different(a):
    b = str(a)
    c = ''
    for e in b:
        if e in c:
            return False
        c += e
    return True

def compare(a,b):
    bools = 0
    cows = 0
    for e1,e2 in zip(list(str(a)),list(str(b))):
        if e1 in b:
            cows += 1
        if e1 == e2:
            cows -= 1
            bools += 1
    return [str(bools),str(cows)]


def step(choice, responce, choices):
    res = []
    for e in choices:
        if compare(e,choice)==responce:
            res.append(e)
    return res

def find_exact_digits(choices):
    try:
        candidate = str(choices[0])
    except E:
        return []
    res = list(str(candidate))

    for num in choices:
        for dig in res:
            if dig not in str(num):
                res.remove(dig)

    return res




def check_digits(choices):
    digits = []
    for num in choices:
        for digit in str(num):
            if digit not in digits:
                digits.append(digit)

    no_digits = [str(e) for e in range(10) if str(e) not in digits]
    return [sorted(digits),sorted(no_digits)]

def beauty_print(choices):
    print(choices)



def my_input(history, choices):
    print('-'*100)
    print(f'Step {len(history)+1}.')
    a = input('Number asked: ')
    b = input('Responce bools: ')
    c = input('Responce cows: ')
    print('-'*100)
    history.append(a+': ' + b+'b,' + c + 'c')
    if b != '3':
        new_choices = step(a,[b,c], choices)
        print('-'*100)
        print(f'Possible Numbers({str(len(new_choices))}): ')
        beauty_print(new_choices)
        print('Possible Digits: ', check_digits(new_choices)[0])
        print('Impossible Digits: ', check_digits(new_choices)[1])
        print('100% Digits: ', find_exact_digits(new_choices))
        print('History: ', history)
        print('-'*100)
        return my_input(history, new_choices)
    else:
        print('You win')


def main():
    print('New game started.')
    a = int(input('Do you play with 3 or 4 digits? \nType number 3/4: '))
    choices = [i for i in range(10**(a-1),10**a,1) if is_different(i)]



    my_input([], choices)

if __name__=='__main__':
    print('You entered Bools and Cows solver. Do you want to start a new game? I will help you to win.')
    print('This program will assist you to win a game against human. Just type the number you guessed and responce by opponent')

    a = input('\ny/n: ')
    if ((a=='y') or a=='Y' or a=='yes' or a =='Yes'):
        main()

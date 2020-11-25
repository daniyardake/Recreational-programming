

# Write a function that returns a list of n number representations, starting at
# zero, with the "digits" selected from a supplied alphabet.

# To illustrate, the alphabet for base 8 would be "01234567" and for base 16 the
# alphabet would be "01234567890ABCDEF".  For the purposes of this function, the
# alphabet could be any combination of characters.  The first char will represent
# 0 (zero), the next character 1, and so forth.  The base is implied by the number
# of characters in the alphabet.  



def alpha_range(n:int, alpha:str) -> list:
    base = len(alpha)
    back_dict = { alpha[i]:i for i in range(base) }
    
    
    def plus_one(str_num):
        new_str = str_num.copy()
        str_len = len(new_str)
        increased = False
        for i in range(str_len):
            num_char = back_dict[new_str[str_len - 1 - i]]
            if num_char != base - 1:
                new_str[str_len - 1 - i] = alpha[num_char + 1]
                for j in range(i):
                    new_str[str_len - 1 - j] = alpha[0]
                increased = True
                break
        
        if not increased:
            if base > 1:
                new_char = alpha[1]
            else:
                new_char = alpha[0]
            new_str = [new_char] + [alpha[0]] * len(new_str)

        return new_str
    
    preset = []
    if n > 0:
        preset.append([alpha[0]])
    for i in range(n - 1):
        preset.append(plus_one(preset[- 1]))
    str_preset = []
    for i in range(len(preset)):
        str_preset.append(''.join(preset[i]))
    return str_preset

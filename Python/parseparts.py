# Write a function to parse a delimited string into component parts and return
# the parts as a dict.  The names of the parts are ordered and supplied as an
# input list.  If a part does not appear, return None for that part.  Extra
# parts in the string may be ignored



def parse_parts(src:str, delim:str, parts:list) -> dict:
    splitted = src.split(delim)
    if (len(splitted)<len(parts)):
        splitted += [None]* (len(parts)-len(splitted))
    return dict(zip(parts,splitted))
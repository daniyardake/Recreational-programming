# Write a function that converts a nested dict with string keys into an
# un-nested dict with keys that are the "path" to the values.



def flatten_dict(d:dict, s:str) -> dict:
    def my_flatten(d, s, before = ''):
        try:      
            return {
                before+s+key if before else key :value
                for key_in_key,value_in_value in d.items()
                for key,value in my_flatten(value_in_value, s, key_in_key).items()
                
            }
        except:
            return {
                before:d
                }
    
    return my_flatten(d,s)


    


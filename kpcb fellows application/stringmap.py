class StringMap:
    
    class MapEntry:
        def __init__(self, key, value):
            self.key = key
            self.value = value


    def __init__(self, size):
        self.size = 0
        self.max_size = size
        self.table = [None]*size


    def set(self, key, value):
        # We don't want our map to take in null keys or values
        # (if you want to "set" a key's value to null, you should delete it)
        if (key is None) or (value is None) or (not key isinstance(basestring)):
            return false
        else:
            i_curr = hash(key)
    def get(self, key):

    def delete(self, key):

    def load(self):
        return float(size) / float(max_size)
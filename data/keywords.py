from __future__ import absolute_import
from __future__ import print_function
import os
import rake


# spanish stopwords
stoppath = "SpanishStoplist.txt"

# 1. initialize RAKE by providing a path to a stopwords file and setting phrase length in words to 1
rake_object = rake.Rake(stoppath, 5, 1, 4)

# 2. run on RAKE on a given text

for file in os.listdir():
    if (file.endswith(".txt") and file != 'SmartStoplist.txt'
            and file != 'SpanishStoplist.txt') and not file.startswith('KEY_'):
        rev_file = open(file, 'r', encoding='utf-8')
        text = rev_file.read()
        keywords = rake_object.run(text)
        rev_file.close()
        # 3. print results
        #print("Keywords:", keywords)
        with open('KEY_' + file, 'a', encoding='utf-8') as key_file:
            key_file.write('Keywords:\n')
            for key in keywords:
                key_rec = key[0] + ' ' + str(key[1]) + '\n'
                key_file.write(key_rec)
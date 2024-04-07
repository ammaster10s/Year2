import pandas as pd
import regex as re

def remove_spaces(string):
    return string.strip()

def is_normal_english_format(name):
    return name.title() if name else None

def process_name(name, filtered_words):
    names = []
    if name:
        name_parts = re.split(r';|,|\(([^()]*)\)', name)
        for part in name_parts:
            if part:
                part = part.strip()
                part = re.sub('|'.join(map(re.escape, filtered_words)), '', part).strip()
                part = is_normal_english_format(part)
                if part:
                    thai_word, english_word = tuple(check_Name(part))
                    if thai_word:
                        names.append(thai_word)
                    if english_word:
                        names.append(english_word)
                elif part:
                    names.append(part)
    return names

if __name__ == '__main__':
    # Define the words to filter out
    filtered_words = ["ผู้ทรงคุณวุฒิ","ผศ.","รายละเอียดบทความ","กองบรรณาธิการ","สารบัญ","คำแนะนำสำหรับผู้เขียน","ผู้ช่วยศาสตราจารย์","ผู้แต่ง","รองศาสตราจารย์","ดร.","รศ.", "dr.", "ทันตแพทย์หญิง","ศ.", "นาย", "นาง", "นางสาว", "พระครู","ผศ.","พระมหา", "นพ.","Authors"," :","Dr.","*","- -",":"," -","-- --" ,"ร.ต.อ.","อ.ด.ร.","Assoc.Prof.","Author","พระ","Mr.", "Ms."]
    # Thai alphabet list
  
    char = {'id': [], 'name': []}
    df = pd.read_csv('/kaggle/input/thaijo-researcher/test.csv')
    df = df.fillna('')
    for idx, row in df.iterrows():
        names = []
        char['id'].extend([f"{row['_id']}_{i+1}" for i in range(10)])
        author = row['_source.author']
        if author != '':
            # Using regular expressions to split author's name and extract text within parentheses
            authors = re.split(r';|,|\(([^()]*)\)', author)  # Add semicolon to the regex pattern
            for a in authors:
                if a is not None:  # Add a check to ensure a is not None
                    a = a.strip()  # Remove leading and trailing spaces
                    # Remove title words from the name
                    for title in filtered_words:
                        a = a.replace(title, "").strip()
                    a = is_normal_english_format(a)  # Check if a is in normal English format and convert if necessary
                    if a and a not in names:  # Check if a is not empty, not in filtered words, and not already in names
                        names.append(a)
        co_author = row['_source.co-author']                
        if co_author != '':
            # Using regular expressions to split by commas and extract text within parentheses
            ca = re.split(r';|,|\(([^()]*)\)', co_author)  # Add semicolon to the regex pattern
            for c in ca:
                if c is not None:  # Add a check to ensure c is not None
                    c = c.strip()  # Remove leading and trailing spaces
                    # Remove title words from the name
                    for title in filtered_words:
                        c = c.replace(title, "").strip()
                    c = is_normal_english_format(c)  # Check if c is in normal English format and convert if necessary
                    if c and c not in names:  # Check if c is not empty, not in filtered words, and not already in names
                        names.append(c)
                        
        if len(names) == 0:
            char['name'].extend([None]*10)
            continue
        names = [remove_spaces(name) for name in names]
        if len(names) > 10:
            names = names[:10]
        elif len(names) < 10:
            names.extend(['']*(10-len(names)))
        char['name'].extend(names)
    char = pd.DataFrame(char)
    char.to_csv('/kaggle/working/predict.csv', index=False)

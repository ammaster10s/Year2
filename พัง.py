a = "-- --123"
filtered_words = ["ผู้ทรงคุณวุฒิ","ผศ.","รายละเอียดบทความ","กองบรรณาธิการ","สารบัญ","คำแนะนำสำหรับผู้เขียน","ผู้ช่วยศาสตราจารย์","ผู้แต่ง","รองศาสตราจารย์","ดร.",
                  "รศ.", "dr.", "ทันตแพทย์หญิง","ศ.","นางสาว", "นาง",  "พระครู","ผศ.",
                  "พระมหา", "นพ.","Authors"," :","Dr.","*",":","-- --" ,"ร.ต.อ.","อ.ด.ร.","อ.","Assoc.Prof.","Asst.Prof.","Author","Ven.","พระ",
                  "Mr.","Ms.","Phra","M.R","M.D.","ว่าที่ พ.ต." ,"ม.ร.ว.",",",". .","- -","--"," -","et al"]

# Iterate over each title in filtered_words
for title in filtered_words:
    # Replace the exact match of the title with an empty string and strip the string
    a = a.replace(title, "").strip()

# Print the result
print(a)

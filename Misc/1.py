namelist = ["Satawat", "Pisit", "Thanaphong", "Pished", "Nut", "Amon"]
check = ""
while check != "q":
    print('Student list:', namelist)
    check = input(
        "Please enter a student name that you want to delete (q=exit): ")
    if check in namelist:
        print("You will remove '%s' from this class" % check)
        check1 = input("Please type (Confor 'y' , Cancel 'n'): ")
        if check1 == "y":
            namelist.remove(check)

        if check1 == "n":
            continue
    if check == "q":

        break

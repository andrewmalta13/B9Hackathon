import sys
import urllib2              # working with url
import json
import re

sys.path.insert(0, 'libraries') # import the libraries that we need to the GAE project

from bs4 import BeautifulSoup


def courseNumberTest(courseNumber,termNumber):                 #function will iterate through range of possible course numbers testing to see if they exist
    try:
        url = urllib2.urlopen("http://students.yale.edu/oci/resultDetail.jsp?course=%s&term=%s" % (courseNumber,termNumber))
        soup = BeautifulSoup(url.read())            #generates Beautiful Soup object for site
        pageTextWithExtraComments = soup.get_text()         #random commens seem to come along for the ride
        pageTextLength = len(pageTextWithExtraComments)
        endComments = pageTextWithExtraComments.rfind("-->",0,pageTextLength) + 3       #point in page when stops being comments
        return pageTextWithExtraComments[endComments:pageTextLength]        #get part of page that is actually text
    except:
        return None

##Will be deleted when we know it works

def parseCourseText(fullcourseinfo):

    infoarray = fullcourseinfo.split("\n")
    list_of_items = []
    check = False
    
    for element in infoarray: #Removes all blank fields and whitespace
        if element != "":
            for character in element:
                if character.isalpha() or character.isdigit():
                    check = True
        if check:           
            list_of_items.append(element)
            check = False




    list_of_possible_fields = ["Department","CourseNumber", "alternateDept","courseName","professor","timeLocation","term",
    "departmentPermissionRequired","final","Areas","Skills","instructorPermissionRequired","readingPeriod","YCnote", "description"
    ,"bonusField"]

        #list_of_items defined in try. is courseinfoarray

    bool_dict = {
    "Department" : lambda item: True,
    "CourseNumber": lambda item: True,
    "alternateDept": lambda item: item.find("/") >= 0,
    "courseName": lambda item: True, #Always present
    "professor": lambda item: re.match(r"\D(\D*\s)*\D*$",item) != None,
    "timeLocation": lambda item: not ("Spring" in item or ("Fall" in item or "Summer" in item)),
    #"location" :lambda item:
    "term": lambda item: item.startswith("Spring") or (item.startswith("Fall") or item.startswith("Summer")),
    "departmentPermissionRequired": lambda item: "Pre-Approval" in item,
    "final":lambda item: "Final exam scheduled" in item or "No regular final examination" in item,
    "Areas":lambda item: item.startswith("Areas"),
    "Skills": lambda item: item.startswith("Skills"),
    "instructorPermissionRequired": lambda item: "Permission of instructor required" in item,
    "readingPeriod": lambda item: "Meets during reading period" == item,
    "YCnote": lambda item: "YC" in item and ":" in item,
    "description": lambda item: True,
    "bonusField": lambda item: True
    }


    initialDictionary = {   "Department" : "",
                            "CourseNumber" : "", 
                            "alternateDept" : "",
                            "courseName" : "",
                            "professor" : "",
                            "timeLocation" : "",
                            "term" : "",
                            "departmentPermissionRequired" : "",
                            "final" : "",
                            "Areas" : "",
                            "Skills" : "",
                            "instructorPermissionRequired" : "",
                            "readingPeriod" : "",
                            "YCnote" : "",
                            "description" : "",
                            "bonusField" : ""
                        }


    index = 0

    for element in list_of_items:
        while index < (len(list_of_possible_fields)):
            #print element+ " " + list_of_possible_fields[index]
            
            if bool_dict[list_of_possible_fields[index]](element):
                initialDictionary[list_of_possible_fields[index]] = element
                if list_of_possible_fields[index] != "professor":
                    list_of_possible_fields.remove(list_of_possible_fields[index])
                elif list_of_possible_fields[index] == "timeLocation":
                    list_of_possible_fields.remove("professor")
                    list_of_possible_fields.remove("timeLocation")
                break
            else:
                list_of_possible_fields.remove(list_of_possible_fields[index])
                index -= 1
            
            index += 1

    cleanDictionary = {}
    
    for key, value in initialDictionary.iteritems():
        cleanDictionary[key] = value.strip()
    print cleanDictionary
    finalDictionary = {"courseName":cleanDictionary["courseName"],
                       "professor":cleanDictionary["professor"],
                       "time": "",
                       "location": "",
                       "distReqAreas":"",
                       "term":cleanDictionary["term"],
                       "description": "", #cleanDictionary["description"],
                       "instructorPermissionRequired": cleanDictionary["instructorPermissionRequired"],
                       "departmentPermissionRequired":False,
                       "finalDescription":cleanDictionary["final"],
                       "readingPeriod":False,
                       "classRating":0.0,
                       "professorRating":0.0,
                       "workRating":0.0,
                       "courseNum":""
                        }

    #inputing more complicated fields into finalDictionary that need changing from cleanDictionary

    #time and location
    temp = cleanDictionary["timeLocation"].split()
    if len(temp)==2:
        finalDictionary["time"] = temp[0] + " " + temp[1]
    elif len(temp)>2:
        finalDictionary["time"] = temp[0] + " " + temp[1]
        for i in range(2,len(temp)-1):
            finalDictionary["location"] = finalDictionary["location"] + temp[i] + " "
        finalDictionary["location"] = finalDictionary["location"] + temp[len(temp)-1]
        #haven't dealt with the case where a course just has a location and no time, but this seems unlikely

    #distReqAreas
    if cleanDictionary["Skills"]!="" and cleanDictionary["Areas"]!="":
        finalDictionary["distReqAreas"] = cleanDictionary["Skills"] + ", " + cleanDictionary["Areas"]
    elif cleanDictionary["Skills"]=="":
        finalDictionary["distReqAreas"] = cleanDictionary["Areas"]
    elif cleanDictionary["Areas"]=="":
        finalDictionary["distReqAreas"] = cleanDictionary["Skills"]

    #departmentPermisionRequired
    if cleanDictionary["departmentPermissionRequired"] != "":
        finalDictionary["departmentPermissionRequired"] = True

    #readingPeriod
    if cleanDictionary["readingPeriod"] != "":
        finalDictionary["readingPeriod"] = True

    #courseNum
    temp2 = cleanDictionary["CourseNumber"].split()
    finalDictionary["courseNum"] = cleanDictionary["Department"] + " " + temp2[0]+ " " +temp2[1]

    #combine description and bonus field
    if cleanDictionary["description"] != "" and cleanDictionary["bonusField"] != "":
        finalDictionary["description"] =  cleanDictionary["description"] + " " + cleanDictionary["bonusField"]
    elif cleanDictionary["description"] != "":
        finalDictionary["description"] = cleanDictionary["description"]
    elif cleanDictionary["bonusField"] != "":
        finalDictionary["description"] = cleanDictionary["bonusField"]


    return finalDictionary

# print parseCourseText(courseNumberTest(22422,201501)) #test code. leaving until we are sure is bug free
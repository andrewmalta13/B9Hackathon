import webapp2
import json
import OCIScraper

from google.appengine.ext import ndb

#ndb model class for each Course that we are storing.
class Course(ndb.Model):
    title = ndb.StringProperty()
    professor = ndb.StringProperty()
    time = ndb.StringProperty()
    location = ndb.StringProperty()
    distReqAreas = ndb.StringProperty()
    term = ndb.StringProperty()
    instructorPermissionRequired = ndb.StringProperty()
    final = ndb.StringProperty()
    courseNum = ndb.StringProperty()

    description = ndb.TextProperty()

    departmentPermissionRequired = ndb.BooleanProperty()
    readingPeriod = ndb.BooleanProperty()

    classRating = ndb.FloatProperty() 
    professorRating = ndb.FloatProperty()
    workRating = ndb.FloatProperty()

    

class JSONHandler(webapp2.RequestHandler):
    def get(self):
        courses = ndb.gql("SELECT * FROM Course")
        coursesjson = {"courses": []}
        for course in courses:
            coursesjson.append({"title": course.title,
                                "professor": course.professor,
                                "time": course.time,
                                "location": course.location,
                                "distReqAreas": course.disReqAreas,
                                "term": course.term,
                                "description": course.description,
                                "instructorPermissionRequired": course.instructorPermissionRequired,
                                "departmentPermissionRequired": course.departmentPermissionRequired,
                                "readingPeriod":course.readingPeriod,
                                "classRating": course.classRating,
                                "professorRating": course.professorRating,
                                "workRating": course.workRating,
                                "courseNum": course.courseNum})

        self.response.write(json.dumps(coursesjson))



class FetchCoursesHandler(webapp2.RequestHandler):
    def get(self):
        for i in range (20001, 30000):
          courseText = OCIScraper.courseNumberTest(i)
          if courseText:
            courseInfoDict = OCIScraper.parseCourseText(courseText)
            c = Course(title = courseInfoDict["courseName"],
                       professor = courseInfoDict["professor"],
                       time = courseInfoDict["time"],
                       location = courseInfoDict["location"],
                       distReqAreas = courseInfoDict["distReqAreas"],
                       term = courseInfoDict["term"],
                       description = courseInfoDict["description"],
                       instructorPermissionRequired = courseInfoDict["instructorPermissionRequired"],
                       departmentPermissionRequired = courseInfoDict["departmentPermissionRequired"],
                       readingPeriod = courseInfoDict["readingPeriod"],
                       classRating = courseInfoDict["classRating"],
                       professorRating = courseInfoDict["professorRating"],
                       workRating = courseInfoDict["workRating"],
                       courseNum = courseInfoDict["courseNum"])
            c.put()


app = webapp2.WSGIApplication([
    ('/courses.json', JSONHandler),
    ('/fetch', FetchCoursesHandler)
], debug=True)

import webapp2
import json
import coursestats

from google.appengine.ext import ndb

#ndb model class for each Course that we are storing.
class Course(ndb.Model):
    title = ndb.StringProperty()
    professor = ndb.StringProperty()
    time = ndb.StringProperty()
    location = ndb.StringProperty()
    distReqAreas = ndb.StringProperty()
    term = ndb.StringProperty()

    description = ndb.TextProperty()

    permissionRequired = ndb.BooleanProperty()
    readingPeriod = ndb.BooleanProperty()

    classRating = ndb.floatProperty() 
    professorRating = ndb.floatProperty()
    workRating = ndb.floatProperty()

    courseNum = ndb.IntegerProperty()

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
                                "permissionRequired": course.permissionRequired,
                                "readingPeriod":course.readingPeriod,
                                "classRating": course.classRating,
                                "professorRating": course.professorRating,
                                "workRating": course.workRating,
                                "courseNum": course.courseNum})
        self.response.write(json.dumps(coursesjson))



class FetchCoursesHandler(webapp2.RequestHandler):
    def get(self):
        #run code from the OCI Parsing script to generate course objects
        self.response.out.write("module not implemented") 



app = webapp2.WSGIApplication([
    ('/courses.json', JSONHandler),
    ('/fetch', FetchCoursesHandler)
], debug=True)

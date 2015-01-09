import webapp2
import json
import coursestats


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

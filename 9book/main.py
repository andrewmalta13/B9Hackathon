import webapp2
import json
from google.appengine.ext import ndb

class MainHandler(webapp2.RequestHandler):
    

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
      


    def get(self):
        self.response.write('Hello world!')

app = webapp2.WSGIApplication([
    ('/', MainHandler)
], debug=True)

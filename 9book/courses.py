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
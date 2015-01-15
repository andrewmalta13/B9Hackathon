import webapp2


class MainPage(webapp2.RequestHandler):
  def get(self):
    self.response.out.write("This is the main page.")
    

app = webapp2.WSGIApplication([('/', MainPage)],
                              debug=True)


from zeep import Client

controller = Client('src/main/resources/ControllerService.wsdl').service

temp = controller.getScreenshot()
# print(temp)
# print(temp['bytes'])
with open("screenshot.jpg", "wb") as f:
    f.write(temp['bytes'])
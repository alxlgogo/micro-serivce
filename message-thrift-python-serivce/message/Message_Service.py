import smtplib
from email.header import Header
from email.mime.text import MIMEText

from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
from thrift.transport import TSocket, TTransport

from message.api import MessageService

sender = "alxlgogo@gmail.com"
authCode = "aaa"


class MessageServicehandler:
    def sendMobileMessage(self, mobile, message):
        print("sendMobileMessage, mobile: " + mobile + ", message: " + message)
        return True

    def sendEmailMessage(self, email, message):
        print("sendEmailMessage")
        messageObj = MIMEText(message, "plain", "utf-8")
        messageObj["From"] = sender
        messageObj["to"] = email
        messageObj["subject"] = Header("Wenjing's message", "UTF-8")
        try:
            smtpObj = smtplib.SMTP("smpt.google.com")
            smtpObj.login(sender, authCode)
            smtpObj.sendmail(sender, [email], messageObj.as_string())
            print("send email success.")
            return True
        except smtplib.SMTPException as ex:
            print("send email failed!")
            print(ex)
            return False


if __name__ == '__main__':
    handler = MessageServicehandler()
    # use which processor
    processor = MessageService.Processor(handler)
    # use which port
    transport = TSocket.TServerSocket("127.0.0.1", "9090")
    # use Factory transport
    tfactory = TTransport.TFramedTransportFactory()
    # use TBinary trans protocol
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)
    print("python thrift server start")
    server.serve()
    print("python thrift server exit")

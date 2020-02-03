using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;


namespace tcp
{
    class Program
    {
        private const int portnum = 8023;
        private const string hostname = "10.0.87.138";
        //hostname是服务器当前网络下的ip
        static void Main(string[] args)
        {
            Socket clientSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            clientSocket.Connect(hostname, portnum);
            //Connect方法使用指定的IP地址和端口号将客户端连接到远程TCP主机
            Console.WriteLine("Connect Complete");
            try
            {
                if (clientSocket.Connected)
                {
                    // 如果客户与服务器有连接，并且还未断，则允许发送信息

                    if (clientSocket != null)
                    {
                        while (true)
                        {
                            System.Threading.Thread.Sleep(1000);
                            //Console.WriteLine("Input string:");
                            //获取黑框上的输入字符串
                            DateTime dt = new DateTime();
                            String guid = Guid.NewGuid().ToString();
                            dt = System.DateTime.Now;
                            string strFu = dt.ToString("yyyy-MM-dd HH:mm:ss");
                            Random ran = new Random();
                            int n = ran.Next(100, 1000);
                            string s = "type:" + " data " + "id:" + " 1001 " + "sn: " +guid + " power: " + n + " state: " + "on" + " time: " + strFu;
                            
                            byte[] data = System.Text.Encoding.Default.GetBytes(s);
                            
                            clientSocket.Send(data);
                            Console.WriteLine("Send Complete");

                            //接受打印服务端反馈信息
                            byte[] bytes = new byte[1024];
                            int bytesRec = clientSocket.Receive(bytes);
                            string msgSrv = Encoding.ASCII.GetString(bytes, 0, bytesRec);
                            Console.WriteLine("{0}", msgSrv);


                        }
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Error!" + e.StackTrace);
                Console.ReadLine();
            }
        }
    }
}
using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.IO;
using MySql.Data;
using MySql.Data.MySqlClient;

public class SynchronousSocketListener
{

    // Incoming data from the client.
    public static string data = null;

    public static void StartListening()
    {
        // Data buffer for incoming data.
        byte[] bytes = new Byte[1024];

        // Establish the local endpoint for the socket.
        // Dns.GetHostName returns the name of the 
        // host running the application.
        IPHostEntry ipHostInfo = Dns.GetHostEntry(Dns.GetHostName());
        IPAddress ipAddress = null;



        for (int i = 0; i < ipHostInfo.AddressList.Length; i++)
        {
            //从IP地址列表中筛选出IPv4类型的IP地址
            //AddressFamily.InterNetwork表示此IP为IPv4,
            //AddressFamily.InterNetworkV6表示此地址为IPv6类型
            if (ipHostInfo.AddressList[i].AddressFamily == AddressFamily.InterNetwork)
            {
                ipAddress = ipHostInfo.AddressList[i];
                Console.WriteLine(ipAddress);
                Console.WriteLine(i);
            }
        }



        IPEndPoint localEndPoint = new IPEndPoint(ipAddress, 8023);

        // Create a TCP/IP socket.
        Socket listener = new Socket(AddressFamily.InterNetwork,
            SocketType.Stream, ProtocolType.Tcp);

        // Bind the socket to the local endpoint and 
        // listen for incoming connections.
        try
        {
            listener.Bind(localEndPoint);
            listener.Listen(10);

            // Start listening for connections.
            while (true)
            {
                Console.WriteLine("\nWaiting for a connection...");
                // Program is suspended while waiting for an incoming connection.
                Socket handler = listener.Accept();
                data = null;

                //存放即时信息
                string msgnow = null; 
                string msgBack = "The Server received!";
                byte[] msgBackBite = Encoding.ASCII.GetBytes(msgBack);

                // An incoming connection needs to be processed.
                while (true)
                {

                    bytes = new byte[1024];
                    int bytesRec = handler.Receive(bytes);
                    data += Encoding.ASCII.GetString(bytes, 0, bytesRec);
                    //赋值即时信息
                    msgnow = Encoding.ASCII.GetString(bytes, 0, bytesRec);
                    //打印即时信息
                    Console.WriteLine("Text received now: {0}", msgnow);


                    string type1 = msgnow.Split(' ')[1];
                    string id1 = msgnow.Split(' ')[3];
                    string sn1 = msgnow.Split(' ')[5];
                    string power1 = msgnow.Split(' ')[7];
                    string state1 = msgnow.Split(' ')[9];
                    string time1 = msgnow.Split(' ')[11]+ ' '+ msgnow.Split(' ')[12];

                    Console.WriteLine(type1);
                    Console.WriteLine(id1);
                    Console.WriteLine(sn1);
                    Console.WriteLine(power1);
                    Console.WriteLine(state1);
                    Console.WriteLine(time1);
                    Console.WriteLine("写入完毕！");

                    string str = "server=localhost;User Id=root;password=root;Database=server";//连接MySQL的字符串
                    MySqlConnection mycon = new MySqlConnection(str);//实例化链接
                    mycon.Open();//开启连接
                    MySqlCommand mycmd = new MySqlCommand("insert into tcp(type,id,sn,power,state,time) values('" + type1 + "','" + id1 + "','" + sn1 + "','" + power1 + "','" + state1 + "','" + time1 + "')", mycon);
                    if (mycmd.ExecuteNonQuery() > 0)
                    {
                        Console.WriteLine("数据插入成功！");
                    }
                    

                    
                    //给客户端发送发聩信息
                    handler.Send(msgBackBite);

                    // Console.ReadLine();
                    mycon.Close();//关闭
                    if (msgnow.Contains("\r\n"))
                    {
                        break;
                    }


                }

                

                // Echo the data back to the client.
                byte[] msg = Encoding.ASCII.GetBytes(data);

                handler.Send(msg);
                handler.Shutdown(SocketShutdown.Both);
                handler.Close();
            }


        }
        catch (Exception e)
        {
            Console.WriteLine(e.ToString());
        }

        Console.WriteLine("\nPress ENTER to continue...");
        Console.Read();

    }


    public static int Main(String[] args)
    {
        StartListening();
        return 0;
    }
}
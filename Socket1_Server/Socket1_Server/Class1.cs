using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace ceshi
{
    class Class1
    {
        public class JObject
        {
            /// <summary>
            /// 
            /// </summary>
            public string type { get; set; }
            /// <summary>
            /// 
            /// </summary>
            public string id { get; set; }
            /// <summary>
            /// 
            /// </summary>
            public string sn { get; set; }
            /// <summary>
            /// 
            /// </summary>
            public string power { get; set; }
            /// <summary>
            /// 
            /// </summary>
            public string state { get; set; }
            /// <summary>
            /// 
            /// </summary>
            public string time { get; set; }
        }
        public string[] splitdata(string strdata)
        {
            string[] strdatass = new string[6];
            JObject rt = JsonConvert.DeserializeObject<JObject>(strdata);
            strdatass[0] = rt.type;
            strdatass[1] = rt.id;
            strdatass[2] = rt.sn;
            strdatass[3] = rt.power;
            strdatass[4] = rt.state;
            strdatass[5] = rt.time;

            return strdatass;
        }

    }
}

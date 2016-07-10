#include<reg52.h>
#include <intrins.h>
#define uchar unsigned char
#define uint unsigned int
#define aa 0x3D
sbit         cs=P0^7;         //芯片选择信号,控制芯片的启动和结果读取,低电平有效
sbit         rd=P3^7;         //读数据控制,低电平有效
sbit         wr=P3^6;         //AD转换起动控制,上升沿有效
uchar enable=0;
uchar flag=0;
sbit dula=P2^6;  //数码管段选,锁存器控制信号
sbit wela=P2^7; 
sbit dat=P1^0;//指示灯
sbit bell=P2^3;//响铃
sbit out=P2^2;//数据
 //数码管位选,锁存器控制信号
sfr IAP_CONTR=0xE7;
uchar led[10]={0x3f,0x06,0x5b,0x4f,0x66,0x6d,0x7d,0x07,0x7f,0x6f};
//5ms延时子程序/////////////////
void delay(uint z)
{       uint i,j;
        for(i=z;i>0;i--)
        for(j=210;j>0;j--);
}
////////////////////////////////////////////////////////
void start_ad(void)
{       wela=1;
		cs=0;                 //允许进行A/D转换
        wr=0;
        _nop_();
        wr=1; //WR由低变高时,AD开始转换
    	cs=1;                 //停止AD转换
}
       read_ad()
    {   uint ad_data;
       	P1=0xff;
		wela=1;
        cs=0;                 //允许读
        rd=0;                 //读取转换数据结果数据结果
        _nop_();
        ad_data=P1;         //把数据存到ad_data中
		wela=1;
        rd=1;
        cs=1;            //停止A/D读取
        return(ad_data);
}
//void start_ad()
//{
//	wela=1;
//	P0=0x7f;
//	wela=0;
//	wr=1;
//	_nop_();
//	wr=0;
//	_nop_();
//	wr=1;
//}
//int read_ad()
//{
//	uint ad_data;
//	P1=0xFF;
//	rd=1;
//	_nop_();
//	rd=0;
//	_nop_();
//	ad_data=P1;
//	rd=1;
//	return  ad_data;
//}
//数据处理与显示子程序//////////////////////////////////////
//说明:当输入电压为5V时,A/D输出为FFH,即输入电压=AD数据*(5/255)=AD数据/(255/5)=AD数据/51
void data_shout(uint ad_data)
{       uint a=50,one,two,three,four;
         four=(ad_data/51);                     //第一位
         three=(ad_data%51*10/51);              //第二位
         two=(ad_data%51*10%51*10/51);          //第三位
         one=(ad_data%51*10%51*10%51*10/51); //第四位数码管(最低位)
        while(a--)
        {   
	    	dula=0;		
			P0 = led[one]; //最低位
			dula=1;
			dula=0;
	
			wela=0;
			P0=0xf7;   
			wela=1;
			wela=0;	
			
			delay(1);
		   
			dula=0;		
			P0 = led[two]; //第三位数码管
			dula=1;
			dula=0;
	
			wela=0;
			P0=0xfb;   
			wela=1;
			wela=0;	

             delay(1);


			dula=0;		
			P0 = led[three]; //第二位数码管
			dula=1;
			dula=0;
	
			wela=0;
			P0=0xfd;   
			wela=1;
			wela=0;	

            delay(1);

			dula=0;		
			P0 = led[four];//第一位数码管
			dula=1;
			dula=0;
	
			wela=0;
			P0=0xfe;   
			wela=1;
			wela=0;	

            delay(1);
         }
}
void Send(uint ad_data)
{
	SBUF=((ad_data/51)+48);			// 发送 十 位 
	while(!TI);
	TI=0;
	SBUF=((ad_data%51*10/51)+48);			// 发送 个 位 
	while(!TI);
	TI=0;
	SBUF=((ad_data%51*10%51*10/51)+48);			// 发送 十 位 
	while(!TI);
	TI=0;
	SBUF=((ad_data%51*10%51*10%51*10/51)+48);			// 发送 个 位 
	while(!TI);
	TI=0;
}
void Init_Com()
{
	TMOD=0x20;
	SCON=0x50;
	TH1=0xfd;
	TL1=0xfd;
	TR1=1;
    REN=1;
    SM0=0;
    SM1=1;
	EA=1;
	ES=1;
	
}

int main(void)
{	uint a=0;
	 Init_Com();
	 enable=0;
	 //out=a;
     while(1)
    {       
			 start_ad();  
			 data_shout(read_ad());           
			if(enable==1)
			{
		       Send(read_ad());
			   //delay(1000);
			   enable=0;
			}
		   
		   /*	if(out)
		 		{
			        //dat=0;
					bell=0;
			        //SBUF='U';		
					//while(!TI);
					//TI=0;
		   		}
				if(out!=1)
				{	//dat=1;
					bell=1;
					//SBUF='A';			
					//while(!TI);
					//TI=0;
				}
				//flag=0;
				
		}	 */
	}
}
			  
 

void inter() interrupt 4
{
	if(RI==1)
	{
		RI=0;
		if(SBUF=='G')
		{
			enable=1;
		}
		if(SBUF=='S')
		{
			enable=0;
		}
		if(SBUF=='I')
		{
			flag=1;
		}
		if(SBUF=='F')
		{
			flag=0;
		}
	}
}


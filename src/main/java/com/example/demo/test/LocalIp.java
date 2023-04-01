package com.example.demo.test;

/**
 * @author cy.W
 * @date 2022-4-26 14:02:51
 *
 */
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
 
 
public class LocalIp {
 
	public static void main(String[] args) throws UnknownHostException {
		test2();
//		System.out.println("本机IP:" + test2());
//		getLocalHostLANAddress();
		String ip = InetAddress.getLocalHost().getHostAddress();
		System.out.println(ip);
	}
	
	public static String getIpAddress() {
	    try {
	      Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
	      InetAddress ip = null;
	      while (allNetInterfaces.hasMoreElements()) {
	        NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
	        if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
	          continue;
	        } else {
	          Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
	          while (addresses.hasMoreElements()) {
	            ip = addresses.nextElement();
	            if (ip instanceof Inet4Address) {
	              return ip.getHostAddress();
	            }
	          }
	        }
	      }
	    } catch (Exception e) {
	    	System.err.println("IP地址获取失败" + e.toString());
	    }
	    return "";
	  }
	
	private static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        System.out.println("----" + inetAddr);
                    	if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress;
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }
	
	public static void test2() {
	    try {
	        Enumeration<NetworkInterface> faces = NetworkInterface.getNetworkInterfaces();
	        while (faces.hasMoreElements()) { // 遍历网络接口
	            NetworkInterface face = faces.nextElement();
	            if (face.isLoopback() || face.isVirtual() || !face.isUp()) {
	                continue;
	            }
	            System.out.print("网络接口名：" + face.getDisplayName() + "，地址：");
	            Enumeration<InetAddress> address = face.getInetAddresses();
	            while (address.hasMoreElements()) { // 遍历网络地址
	                InetAddress addr = address.nextElement();
	                if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress() && !addr.isAnyLocalAddress()) {
	                    System.out.print(addr.getHostAddress() + " ");
	                }
	            }
	            System.out.println("");
	        }
	    } catch (SocketException e) {
	        e.printStackTrace();
	    }
	}
 
}
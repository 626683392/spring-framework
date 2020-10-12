package com.meetkiki.jdkProxy;

import java.lang.reflect.Proxy;

public class JdkProxyMain {


	public static void main(String[] args) {
		// 生成代理文件
		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		IDao dao = (IDao) Proxy.newProxyInstance(
				Dao.class.getClassLoader(),        // 代理类加载器
				Dao.class.getInterfaces(),         // 代理的接口
				new DaoJdkProxy(new Dao()));
		dao.select();
	}


	public interface IDao {
		void select();

		void insert();
	}

	public static class Dao implements IDao {
		public void select() {
			System.out.println("select 1 from student:");
			insert();
		}

		public void insert() {
			System.out.println("insert into ...");
		}

		public final void delete() {
			System.out.println("delete from ...");
		}
	}

}

package aierjun.com.aierjunlibrary.utils.checkworlds;

import android.content.Context;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import aierjun.com.aierjunlibrary.utils.sysout.L;

/**
 * @Description: ��ʼ�����дʿ⣬�����дʼ��뵽HashMap�У�����DFA�㷨ģ��
 * @Project��test
 * @Author : chenming
 * @Date �� 2014��4��20�� ����2:27:06
 * @version 1.0
 */
public class SensitiveWordInit {
	private Context context;
	private String ENCODING = "UTF-8";    //�ַ�����
	@SuppressWarnings("rawtypes")
	public HashMap sensitiveWordMap;
	
	public SensitiveWordInit(){
		super();
	}
	
	/**
	 * @author chenming 
	 * @date 2014��4��20�� ����2:28:32
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public Map initKeyWord(Context context){
		this.context=context;
		try {
			addSensitiveWordToHashMap(readSensitiveWordFile());
			//spring��ȡapplication��Ȼ��application.setAttribute("sensitiveWordMap",sensitiveWordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * ��ȡ���дʿ⣬�����дʷ���HashSet�У�����һ��DFA�㷨ģ�ͣ�<br>
	 * �� = {
	 *      isEnd = 0
	 *      �� = {<br>
	 *      	 isEnd = 1
	 *           �� = {isEnd = 0
	 *                �� = {isEnd = 1}
	 *                }
	 *           ��  = {
	 *           	   isEnd = 0
	 *           		�� = {
	 *           			 isEnd = 1
	 *           			}
	 *           	}
	 *           }
	 *      }
	 *  �� = {
	 *      isEnd = 0
	 *      �� = {
	 *      	isEnd = 0
	 *      	�� = {
	 *              isEnd = 0
	 *              �� = {
	 *                   isEnd = 1
	 *                  }
	 *              }
	 *      	}
	 *      }
	 * @author chenming 
	 * @date 2014��4��20�� ����3:04:20
	 * @param keyWordSet  ���дʿ�
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap();     //��ʼ�����д��������������ݲ���
		String key = null;  
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//����keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //�ؼ���
			nowMap = sensitiveWordMap;
//			sensitiveWordMap.putAll(nowMap);
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //ת����char��
				Object wordMap = nowMap.get(keyChar);       //��ȡ
				
				if(wordMap != null){        //������ڸ�key��ֱ�Ӹ�ֵ
					nowMap = (Map) wordMap;
				} else{     //���������򹹽�һ��map��ͬʱ��isEnd����Ϊ0����Ϊ���������һ��
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //�������һ��
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}
				
				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //���һ��
				}
			}
		}
	}

	/**
	 * ��ȡ���дʿ��е����ݣ���������ӵ�set������
	 * @author chenming 
	 * @date 2014��4��20�� ����2:31:18
	 * @return
	 * @version 1.0
	 * @throws Exception 
	 */
	@SuppressWarnings("resource")
	public Set<String> readSensitiveWordFile() throws Exception{
		Set<String> set = null;
//		set=new HashSet<>();
//		set.add(addDate());
		InputStreamReader read = new InputStreamReader(context.getAssets().open("SensitiveWord.txt"));
		try {
				set = new HashSet<>();
				BufferedReader bufferedReader = new BufferedReader(read);
				String txt = null;
				while((txt = bufferedReader.readLine()) != null){    //��ȡ�ļ������ļ����ݷ��뵽set��
					set.add(txt);
			    }
		} catch (Exception e) {
			throw e;
		}finally{
			read.close();     //�ر��ļ���
		}
		L.d(set.size()+"........");
		return set;
	}

	private String addDate(){
		String text=null;
		try {
//Return an AssetManager instance for your application's package
			InputStream is =context.getAssets().open("SensitiveWord.txt");
			int size = is.available();

			// Read the entire asset into a local byte buffer.
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();

			// Convert the buffer into a string.
			text = new String(buffer, "UTf-8");


			// Finally stick the string into the text view.
		} catch (IOException e) {
			// Should never happen!
			throw new RuntimeException(e);
		}
		return text;
	}

}

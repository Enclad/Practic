/* 
JRE System Library [JavaSE-22] 

Вариант А
 Создать файл XML и соответствующую ему схему XSD.
 При разработке XSD использовать простые и комплексные типы, перечисления, шаблоны и предельные значения.
 Сгенерировать класс, соответствующий данному описанию.
 Создать приложение для разбора XML-документа и инициализации коллекции объектов информацией из XML-файла. 
  Для разбора использовать SAX, DOM и StAX-парсеры. 
  Для сортировки объектов использовать интерфейс Comparator.
 Произвести проверку XML-документа с привлечением XSD.
 Определить метод, производящий преобразование разработанного XMLдокумента в документ, указанный в каждом задании.
  
Задание: 1
 Растения, содержащиеся в оранжерее, имеют следующие характеристики:
  Name — название растения;
  Soil — почва для посадки, которая может быть следующих типов: подзолистая, грунтовая, дерново-подзолистая;
  Origin — место происхождения растения;
  Visual рarameters (должно быть несколько) — внешние параметры: цвет стебля, цвет листьев, средний размер растения;
  Growing tips (должно быть несколько) — предпочтительные условия произрастания: 
   температура (в градусах), 
   освещение (светолюбиво либо нет), 
   полив (мл в неделю);
  Multiplying — размножение: листьями, черенками либо семенами.
  Корневой элемент назвать Flower.
 С помощью XSL преобразовать XML-файл в формат HTML, где отобразить растения по предпочитаемой температуре (по возрастанию).
*/

package ch15;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.*;

public class g15 {
	
	public static void main(String[] args) {
		try {
			File xmlFile = new File("Orang.xml");
			File xsltFile = new File("transform.xsl");
			File outputFile = new File("output.html");
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
			transformer.transform(new StreamSource(xmlFile), new StreamResult(outputFile));
			System.out.println("Преобразование завершено. Результат сохранен в output.html");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

package pyramid;

import exception.CannotBuildPyramidException;

import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    public int[][] buildPyramid(List<Integer> inputNumbers) {
        int size = inputNumbers.size();
        if (size > Integer.MAX_VALUE - 2) {
            throw new CannotBuildPyramidException();
        }
        //Проверка - можно ли из элементов предложенного листа сделать пирамиду
        int columns = 1;
        int rows = 1;
        int quantity = 0;
        while (quantity < size) {
            quantity += rows;
            rows++;
            columns += 2;
        }
        //Определяем количество строк и столбцов, которое будет в пирамиде
        rows -= 1;
        columns -= 2;
        for (int i = 0; i < size; i++) {
            if (inputNumbers.get(i) == null) {
                throw new CannotBuildPyramidException();
            }
        }
        if (quantity != size) {
            throw new CannotBuildPyramidException();
        }
        //Сортируем элементы списка по возрастанию
        Collections.sort(inputNumbers);
        //Создаем матрицу, в которой будем строить пирамиду и заполняем ее нулями
        int[][] pyramid = new int[rows][columns];
        //Находим место, откуда начнется заполнение пирамиды
        int center = (columns / 2);
        //Задаем начальное количество элементов в первой строке
        int count = 1;
        //Задаем начальный сдвиг от центра
        int offset = 0;
        //Задаем индекс первого элемента списка
        int listIndex = 0;
        for (int i = 0; i < rows; i++) {
            int start = center - offset;
            for (int j = 0; j < count * 2; j += 2) {

                pyramid[i][start + j] = inputNumbers.get(listIndex);
                listIndex++;
            }
            offset++;
            count++;
        }
        //Вывод пирамиды
        return pyramid;
    }
}
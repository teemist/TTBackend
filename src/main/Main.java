package main;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // Создаем и инициализируем граф
        List<Node> graf = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            graf.add(new Node(i, false));
        }
        // Соседи первого узла
        graf.get(0).getNeigs().add(graf.get(1));
        graf.get(0).getNeigs().add(graf.get(5));
        // Соседи второго узла
        graf.get(1).getNeigs().add(graf.get(0));
        graf.get(1).getNeigs().add(graf.get(2));
        graf.get(1).getNeigs().add(graf.get(3));
        graf.get(1).getNeigs().add(graf.get(5));
        // Соседи третьего узла
        graf.get(2).getNeigs().add(graf.get(1));
        graf.get(2).getNeigs().add(graf.get(3));
        graf.get(2).getNeigs().add(graf.get(4));
        // Соседи четвертого узла
        graf.get(3).getNeigs().add(graf.get(1));
        graf.get(3).getNeigs().add(graf.get(2));
        graf.get(3).getNeigs().add(graf.get(4));
        graf.get(3).getNeigs().add(graf.get(5));
        // Соседи пятого узла
        graf.get(4).getNeigs().add(graf.get(2));
        graf.get(4).getNeigs().add(graf.get(3));
        graf.get(4).getNeigs().add(graf.get(5));
        // Соседи шестого узла
        graf.get(5).getNeigs().add(graf.get(1));
        graf.get(5).getNeigs().add(graf.get(3));
        graf.get(5).getNeigs().add(graf.get(4));

        // Вывод в лог(файл)
        StringBuilder sbGraf = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sbGraf.append("Соседи узла ").append(i + 1).append(":\n");
            for (Node neig : graf.get(i).getNeigs()) {
                sbGraf.append(neig.getNumber()).append(" ");
            }
            if (i != 5) sbGraf.append("\n");
        }
        try (FileWriter fw = new FileWriter("graf.txt")) {
            fw.append(sbGraf);
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // обход

        Queue<Node> queue = new LinkedList<>();
        graf.get(0).setVisited(true);
        queue.add(graf.get(0));

        List<Integer> path = new ArrayList<>();

        // Пока очередь не пуста, достаю из нее вершину и получаю ее номер
        while (!queue.isEmpty()) {
            int curNum = queue.poll().getNumber();
            path.add(curNum);
            if(curNum != 6) {
                // Перебираю соседей вершины, полученной из очереди
                for (Node neigh : graf.get(curNum - 1).getNeigs()) {
                    // Если сосед не посещен, отмечаю посещенным
                    if (!neigh.isVisited()) {
                        neigh.setVisited(true);
                        // Добавляю его в очередь
                        queue.add(neigh);
                    }
                    if (neigh.getNumber() == 6) {
                        path.add(neigh.getNumber());
                        queue.clear();
                        break;
                    }
                }
            }
        }
        // вывод
        StringBuilder shortestPath = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            shortestPath.append(path.get(i));
            if (i != path.size() - 1)
                shortestPath.append("->");
        }
        System.out.print(shortestPath);
    }
}

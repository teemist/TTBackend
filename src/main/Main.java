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
            graf.add(new Node(i, Integer.MAX_VALUE));
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

        Queue<Node> queue = new LinkedList<>();
        graf.get(0).setDistance(0);
        queue.add(graf.get(0));

        // обход
        List<Integer> path = new ArrayList<>();
        path.add(graf.get(0).getNumber());

        while (!queue.isEmpty()) {
            int curNum = queue.poll().getNumber();
            // Цикл перебора соседей всех узлов
            for (Node neigh : graf.get(curNum - 1).getNeigs()) {
                if (neigh.getDistance() == Integer.MAX_VALUE) {
                    neigh.setDistance(graf.get(curNum).getDistance() + 1);
                    path.add(neigh.getNumber());
                    queue.add(neigh);
                }
                if (neigh.getNumber() == 6) {
                    queue.clear();
                    break;
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

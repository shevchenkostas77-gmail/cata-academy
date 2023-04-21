package com.collections;

public class RedBlackTree<T extends Comparable<T>> {

    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(7);
        tree.insert(5);
        tree.insert(9);
        tree.insert(2);
        tree.insert(6);
    }

    // Внутренний класс, представляющий узел красно-черного дерева
    private static class Node<T> {
        T data; // Данные, хранящиеся в узле
        boolean isRed; // Флаг цвета узла
        Node<T> left, right; // Левый и правый потомки узла

        Node(T data, boolean isRed) {
            this.data = data;
            this.isRed = isRed;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", isRed=" + isRed +
                    '}';
        }
    }

    private Node<T> root; // Корневой узел дерева

    // Метод вставки нового узла в дерево
    public void insert(T data) {
        root = insert(root, data); // Вызов приватного метода вставки
        root.isRed = false; // Установка корня дерева в черный цвет
    }

    // Приватный метод вставки узла в дерево
    private Node<T> insert(Node<T> node, T data) {
        if (node == null) {
            return new Node<>(data, true); // Создание нового красного узла
        }

        // Вставка узла в соответствующее поддерево
        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else {
            return node; // Узел с таким значением уже существует в дереве
        }

        // Проверка нарушения свойств красно-черного дерева
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node); // Выполнение левого поворота
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node); // Выполнение правого поворота
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node); // Инвертирование цветов узлов
        }

        return node;
    }

    // Вспомогательный метод для проверки цвета узла
    private boolean isRed(Node<T> node) {
        return node != null && node.isRed;
    }

    // Метод удаления узла из дерева
//    public void remove(T data) {
//        if (!contains(data)) {
//            return; // Если узел не найден, завершить выполнение метода
//        }
//
//        Node<T> node = root;
//        root = remove(node, data); // Вызов приватного метода удаления
//        if (root != null) {
//            root.isRed = false; // Установка корня дерева в черный цвет
//        }
//    }

    // Приватный метод удаления узла из дерева
    private Node<T> remove(Node<T> node, T data) {
        if (data.compareTo(node.data) < 0) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node); //
            }
            node.left = remove(node.left, data);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (data.compareTo(node.data) == 0 && node.right == null) {
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            if (data.compareTo(node.data) == 0) {
                Node<T> min = findMin(node.right);
                node.data = min.data;
                node.right = deleteMin(node.right);
            } else {
                node.right = remove(node.right, data);
            }
        }

        return balance(node);
    }


    // Вспомогательный метод для выполнения левого поворота
    private Node<T> rotateLeft(Node<T> node) {
        Node<T> x = node.right;
        node.right = x.left;
        x.left = node;
        x.isRed = node.isRed;
        node.isRed = true;
        return x;
    }

    // Вспомогательный метод для выполнения правого поворота
    private Node<T> rotateRight(Node<T> node) {
        Node<T> x = node.left;
        node.left = x.right;
        x.right = node;
        x.isRed = node.isRed;
        node.isRed = true;
        return x;
    }

    // Вспомогательный метод для инвертирования цветов узлов
    private void flipColors(Node<T> node) {
        node.isRed = !node.isRed;
        node.left.isRed = !node.left.isRed;
        node.right.isRed = !node.right.isRed;
    }

    // Вспомогательный метод для перемещения красной вершины влево
    private Node<T> moveRedLeft(Node<T> node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    // Вспомогательный метод для перемещения красной вершины вправо
    private Node<T> moveRedRight(Node<T> node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    // Вспомогательный метод для поиска узла с минимальным значением
    private Node<T> findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Вспомогательный метод для удаления узла с минимальным значением
    private Node<T> deleteMin(Node<T> node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }

    // Вспомогательный метод для балансировки
    private Node<T> balance(Node<T> node) {
        if (isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }
}

/*
- insert(data): метод для добавления нового элемента в красно-черное дерево. Если в дереве уже есть элемент с таким
    значением, то он не будет добавлен повторно. В методе выполняется проверка на то, находится ли текущий узел в
    состоянии 3-подряд красных узлов. Если да, то происходит изменение цветов, чтобы снова получить красно-черное дерево.
    Также происходит проверка на правильный порядок узлов и в зависимости от результата метод вызывает себя для одного из
    поддеревьев.
- remove(data): метод для удаления элемента из красно-черного дерева. Если в дереве нет элемента с таким значением,
    то ничего не происходит. В методе выполняется проверка на то, находится ли текущий узел в состоянии 3-подряд красных
    узлов. Если да, то происходят различные преобразования для получения красно-черного дерева. Если удаляемый элемент
    находится в правом поддереве, то происходят левый поворот и изменение цветов узлов. Если удаляемый элемент находится
    в левом поддереве и у его правого потомка нет левого потомка, то происходит перемещение красной вершины влево и
    изменение цветов узлов. В остальных случаях происходит удаление элемента, как в бинарном дереве поиска. В конце метода
    происходит балансировка дерева.
- isRed(node): вспомогательный метод для проверки цвета узла.
- rotateLeft(node): вспомогательный метод для выполнения левого поворота.
- rotateRight(node): вспомогательный метод для выполнения правого поворота.
- flipColors(node): вспомогательный метод для инвертирования цветов узлов.
- moveRedLeft(node): вспомогательный метод для перемещения красной вершины влево.
- moveRedRight(node): вспомогательный метод для перемещения красной вершины вправо.
- findMin(node): вспомогательный метод для поиска узла с минимальным значением
 */





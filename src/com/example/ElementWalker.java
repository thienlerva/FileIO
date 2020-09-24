package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ElementWalker {

    private final String rootDir;
    ElementWalker(final String dir) {
        rootDir = dir;
    }

    private void traverse() {
        Element d = new DirElement(rootDir);
        d.accept(new Walker());
    }

    public static void main(final String[] args) {
        String dirToList = System.getProperty("user.home") + File.separator + "Documents/testDir";
        ElementWalker t = new ElementWalker(dirToList);
        t.traverse();
    }

    private class Walker implements Visitor {
        public void visit(final DirElement d) {
            System.out.println(d);
            for(Element e:d) {
                e.accept(this);
            }
        }

        public void visit(final FileElement f) {
            System.out.println(f);
        }
    }
}

interface Visitor {
    void visit(DirElement d);
    void visit(FileElement f);
}

abstract class Element {
    protected File rootPath;
    abstract void accept(Visitor v);

    @Override
    public String toString() {
        return rootPath.getName();
    }
}

class FileElement extends Element {
    FileElement(final String path) {
        rootPath = new File(path);
    }

    @Override
    void accept(final Visitor v) {
        v.visit(this);
    }
}

class DirElement extends Element implements Iterable<Element> {
    private final List<Element> elemList;
    DirElement(final String path) {
        elemList = new ArrayList<Element>();
        rootPath = new File(path);
        for (File f : rootPath.listFiles()) {
            if (f.isDirectory()) {
                elemList.add(new DirElement(f.getAbsolutePath()));
            } else if (f.isFile()) {
                elemList.add(new FileElement(f.getAbsolutePath()));
            }
        }
    }

    @Override
    void accept(final Visitor v) {
        v.visit(this);
    }

    public Iterator<Element> iterator() {
        return elemList.iterator();
    }
}

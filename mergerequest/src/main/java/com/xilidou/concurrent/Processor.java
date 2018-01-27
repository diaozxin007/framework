package com.xilidou.concurrent;

import java.util.List;

public interface Processor<T> {

    void process(List<T> list);

}

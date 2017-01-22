package com.example.apputil.realm;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by tujingwu on 2016/11/28.
 * <p/>
 * realm数据库基本工具类
 * 文档https://realm.io/cn/docs/java/latest/#section-28
 * 博客介绍：http://www.cnblogs.com/liushilin/p/5752099.html
 */
public class RealmUtils {

    private static RealmUtils realmUtils;

    public static RealmUtils getInstance() {
        if (realmUtils == null) {
            synchronized (RealmUtils.class) {
                if (realmUtils == null) {
                    realmUtils = new RealmUtils();
                }
            }
        }
        return realmUtils;
    }

    /**
     * ===========================同步增删改查=============================
     */

    /**
     * 单个添加或保存数据
     *
     * @param object
     * @return
     */
    public boolean sava(RealmObject object) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            mRealm.beginTransaction();
            mRealm.copyToRealm(object);
            mRealm.commitTransaction();
            mRealm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            mRealm.close();
            return false;
        }
    }

    /**
     * 批量添加或保存数据
     *
     * @param list
     * @return
     */
    public boolean sava(List<? extends RealmObject> list) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            mRealm.beginTransaction();
            mRealm.copyToRealm(list);
            mRealm.commitTransaction();
            mRealm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            mRealm.close();
            return false;
        }
    }

    /**
     * 删除指定的单个数据
     *
     * @param clazz   来自那个表格
     * @param cds     要删除的条件字段
     * @param content 要删除的具体内容
     * @return 是否删除成功
     * <p/>
     * 这里只给出2个参数 并且都是字符串类型 需要多个参数或其他类型自行扩充
     */
    public boolean delete(Class<? extends RealmObject> clazz, String cds, String content) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            mRealm.beginTransaction();
            mRealm.where(clazz)
                    .equalTo(cds, content)
                    .findFirst()
                    .deleteFromRealm();
            mRealm.commitTransaction();
            mRealm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            mRealm.close();
            return false;
        }
    }

    /**
     * 删除当前表中所有数据
     *
     * @param clazz 指定要删除所有数据的表
     * @return
     */
    public boolean delete(Class<? extends RealmObject> clazz) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            mRealm.beginTransaction();
            mRealm.delete(clazz);
            mRealm.commitTransaction();
            mRealm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            mRealm.close();
            return false;
        }
    }

    /**
     * 修改或更新数据
     * <p/>
     * 例如：要把person表里user_id等于1的名字修改成小明
     *
     * @param clazz   person
     * @param cds     user_id
     * @param content 1
     * @param newData 小明
     * @return
     */
    public boolean update(Class<? extends RealmObject> clazz, String cds, String content, String newData) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            RealmObject first = mRealm.where(clazz)
                    .equalTo(cds, content)
                    .findFirst();
            mRealm.beginTransaction();
            if (first != null)
                ((Person) first).setName(newData);
            mRealm.commitTransaction();
            mRealm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            mRealm.close();
            return false;
        }
    }

    /**
     * 根据对应的某个条件查询
     *
     * @param claszz  要查询的表
     * @param cds     要查询的条件
     * @param content 要查询的内容
     * @return 返回查询的对象
     */
    public RealmObject find(Class<? extends RealmObject> claszz, String cds, String content) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            RealmObject ob = mRealm.where(claszz)
                    .equalTo(cds, content)
                    .findFirst();

            if (ob == null) {
                mRealm.close();
                return null;
            } else {
                RealmObject obj = mRealm.copyFromRealm(ob);//不能直接对数据库对象数据做操作，只能复制一份来做操作
                mRealm.close();
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.close();
            return null;
        }
    }

    /**
     * 查找某个条件的所有数据
     * <p/>
     * 例如要查找 person里名字(name)叫小明的数据
     *
     * @param claszz  person
     * @param cds     name
     * @param content 小明
     * @return
     */
    public List<? extends RealmObject> findAll(Class<? extends RealmObject> claszz, String cds, String content) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            RealmResults<? extends RealmObject> allData = mRealm.where(claszz)
                    .equalTo(cds, content)
                    .findAll();
            if (allData.size() != 0) {
                List<? extends RealmObject> list = mRealm.copyFromRealm(allData);
                mRealm.close();
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.close();
            return null;
        }
    }

    /**
     * 查询某个表的所有数据
     *
     * @param claszz
     * @return
     */
    public List<? extends RealmObject> findAll(Class<? extends RealmObject> claszz) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            RealmResults<? extends RealmObject> allData = mRealm.where(claszz)
                    .findAll();
            if (allData.size() != 0) {
                List<? extends RealmObject> list = mRealm.copyFromRealm(allData);
                mRealm.close();
                return list;
            } else {
                mRealm.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.close();
            return null;
        }
    }


    //============================增删改查异步操作===========================


    /**
     * 异步保存数据
     *
     * @param object
     * @return
     */
    public RealmAsyncTask savaAsync(final RealmObject object) {
        final Realm mRealm = Realm.getDefaultInstance();
        return mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(object);
                realm.close();
                mRealm.close();
            }

        });
    }

    /**
     * 异步批量添加或保存数据
     *
     * @param list
     * @return
     */
    public RealmAsyncTask savaAsync(final List<? extends RealmObject> list) {
        final Realm mRealm = Realm.getDefaultInstance();
        return mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(list);
                realm.close();
                mRealm.close();
            }
        });
    }


    /**
     * 异步删除指定的单个数据
     *
     * @param clazz   来自那个表格
     * @param cds     要删除的条件字段
     * @param content 要删除的具体内容
     * @return 是否删除成功
     * <p/>
     * 这里只给出2个参数 并且都是字符串类型 需要多个参数或其他类型自行扩充
     */
    public RealmAsyncTask deleteAsync(final Class<? extends RealmObject> clazz, final String cds, final String content) {
        final Realm mRealm = Realm.getDefaultInstance();
        return mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(clazz)
                        .equalTo(cds, content)
                        .findFirst()
                        .deleteFromRealm();
                realm.close();
                mRealm.close();
            }
        });
    }

    /**
     * 异步删除当前表中所有数据
     *
     * @param clazz 指定要删除所有数据的表
     * @return
     */
    public RealmAsyncTask deleteAsync(final Class<? extends RealmObject> clazz) {
        final Realm mRealm = Realm.getDefaultInstance();
        return mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(clazz);
                realm.close();
                mRealm.close();
            }
        });
    }

    /**
     * 异步修改或更新数据
     * <p/>
     * 例如：要把person表里user_id等于1的名字修改成小明
     *
     * @param clazz   person
     * @param cds     user_id
     * @param content 1
     * @param newData 小明
     * @return
     */
    public RealmAsyncTask updateAsync(final Class<? extends RealmObject> clazz, final String cds, final String content, String newData) {
        final Realm mRealm = Realm.getDefaultInstance();
        return mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(clazz)
                        .equalTo(cds, content)
                        .findFirst();
                realm.close();
                mRealm.close();
            }
        });
    }

    /**
     * 异步根据对应的某个条件查询
     *
     * @param claszz  要查询的表
     * @param cds     要查询的条件
     * @param content 要查询的内容
     * @return 返回查询的对象
     */
    public RealmObject findAsync(Class<? extends RealmObject> claszz, String cds, String content) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            RealmObject ob = mRealm.where(claszz)
                    .equalTo(cds, content)
                    .findFirstAsync();
            if (ob == null) {
                mRealm.close();
                return null;
            } else {
                RealmObject obj = mRealm.copyFromRealm(ob);
                mRealm.close();
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.close();
            return null;
        }
    }

    /**
     * 异步查找某个条件的所有数据
     * <p/>
     * 例如要查找 person里名字(name)叫小明的数据
     *
     * @param claszz  person
     * @param cds     name
     * @param content 小明
     * @return
     */
    public List<? extends RealmObject> findAllAsync(Class<? extends RealmObject> claszz, String cds, String content) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            RealmResults<? extends RealmObject> allData = mRealm.where(claszz)
                    .equalTo(cds, content)
                    .findAllAsync();
            if (allData.size() != 0) {
                List<? extends RealmObject> list = mRealm.copyFromRealm(allData);
                mRealm.close();
                return list;
            } else {
                mRealm.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            mRealm.close();
            return null;
        }
    }

    /**
     * 异步查询某个表的所有数据
     *
     * @param claszz
     * @return
     */
    public List<? extends RealmObject> findAllAsync(Class<? extends RealmObject> claszz) {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            RealmResults<? extends RealmObject> allData = mRealm.where(claszz)
                    .findAllAsync();
            if (allData.size() != 0) {
                List<? extends RealmObject> list = mRealm.copyFromRealm(allData);
                mRealm.close();
                return list;
            } else {
                mRealm.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.close();
            return null;
        }
    }

    /**
     * 清空数据库
     *
     * @return
     */
    public boolean clearDatabase() {
        Realm mRealm = Realm.getDefaultInstance();
        try {
            mRealm.beginTransaction();
            mRealm.deleteAll();
            mRealm.commitTransaction();
            mRealm.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            mRealm.close();
            return false;
        }
    }

   /* public void closeRealm() {
        if (mRealm != null && !mRealm.isClosed())
            mRealm.close();
    }*/
}

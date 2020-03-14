package com.oreooo.baselibrary.list;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {
    private Context mContext;
    private List<T> mData;
    private int mLayoutId;
    private OnViewHolderClickListener mListener;

    private View mHeaderView;
    private View mFooterView;
    private static final int TYPE_HEADER = 0;  //说明是带有Header的
    private static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    private static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    public BaseRecyclerAdapter(Context context, List<T> list, @IdRes int layoutId,
                               @Nullable OnViewHolderClickListener listener) {
        this.mData = list;
        this.mLayoutId = layoutId;
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new BaseViewHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new BaseViewHolder(mFooterView);
        }
        return BaseViewHolder.
                createViewHolder(mContext, parent, mLayoutId, mListener);
    }

    @Override
    public final void onBindViewHolder(@NonNull BaseRecyclerAdapter.BaseViewHolder holder, int position) {
        final int mPosition = position;
        if (getItemViewType(position) == TYPE_NORMAL && mHeaderView != null) {
            bindHolder(holder, mData.get(position - 1), position);
        } else if (getItemViewType(position) == TYPE_NORMAL && mHeaderView == null) {
            bindHolder(holder, mData.get(position), position);
        } else if (getItemViewType(position) == TYPE_HEADER && mHeaderView != null) {
            bindHeaderHolder(holder);
        } else if (getItemViewType(position) == TYPE_FOOTER && mFooterView != null) {
            bindFooterHolder(holder);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(mPosition, v);
                }
            }
        });
    }

    public abstract void bindHolder(BaseViewHolder holder, T item, int position);

    public void bindHeaderHolder(BaseViewHolder holder) {
    }

    public void bindFooterHolder(BaseViewHolder holder) {
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        } else if (position == 0 && mHeaderView != null) {
            return TYPE_HEADER;
        } else if (position == getItemCount() - 1 && mFooterView != null) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return mData.size();
        } else if (mHeaderView == null || mFooterView == null) {
            return mData.size() + 1;
        } else {
            return mData.size() + 2;
        }
    }

    public void setNewData(List<T> newData) {
        this.mData = newData;
    }

    public List<T> getData() {
        return mData;
    }

    public void setOnViewHolderClickListener(OnViewHolderClickListener listener) {
        this.mListener = listener;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    public Context getAdapterContext() {
        return mContext;
    }

    public interface OnViewHolderClickListener {
        void onClick(int position, View view);
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> mViews;
        private Context mContext;
        private View mView;
        private BaseRecyclerAdapter.OnViewHolderClickListener mListener;

        private BaseViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        private BaseViewHolder(Context context, View itemView,
                               @Nullable BaseRecyclerAdapter.OnViewHolderClickListener listener) {
            super(itemView);
            mViews = new SparseArray<>();
            this.mContext = context;
            this.mView = itemView;
            this.mListener = listener;
        }

        static BaseViewHolder createViewHolder(Context context, View itemView,
                                               @Nullable BaseRecyclerAdapter.OnViewHolderClickListener listener) {
            return new BaseViewHolder(context, itemView, listener);
        }

        static BaseViewHolder createViewHolder
                (Context context, ViewGroup parent, int layoutId,
                 @Nullable BaseRecyclerAdapter.OnViewHolderClickListener listener) {
            return new BaseViewHolder(context, LayoutInflater.from(context).inflate
                    (layoutId, parent, false), listener);
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
    }
}

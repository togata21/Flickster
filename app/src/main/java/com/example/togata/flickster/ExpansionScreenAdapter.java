package com.example.togata.flickster;

/**
 * Created by togata on 6/28/18.
 */

public class ExpansionScreenAdapter{/*} extends BaseAdapter{
    ArrayList<Movie> movies;
    Config config;
    Context context;

    public ExpansionScreenAdapter(ArrayList<Movie> param){
        movies = param;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ExpansionScreenAdapter.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        boolean isPortrait = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        if (isPortrait){
            String imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());
            //load image using glide

            GlideApp.with(context)
                    .load(imageUrl)
                    .transform(new RoundedCornersTransformation(15, 0))
                    .placeholder(R.drawable.flicks_movie_placeholder)
                    .error(R.drawable.flicks_movie_placeholder)
                    .into(holder.ivPoster);
        }

        else{
            String imageUrl = config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath());
            //load image using glide

            GlideApp.with(context)
                    .load(imageUrl)
                    .transform(new RoundedCornersTransformation(15, 0))
                    .placeholder(R.drawable.flicks_backdrop_placeholder)
                    .error(R.drawable.flicks_backdrop_placeholder)
                    .into(holder.ivBackdrop);
        }








    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivBackdrop;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
            ivBackdrop = (ImageView) itemView.findViewById(R.id.ivBackdrop);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
        }
    }
    */

}
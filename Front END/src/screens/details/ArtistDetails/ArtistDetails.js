import Typography from "@material-ui/core/Typography";
import StarBorderIcon from "@material-ui/icons/StarBorder";
import {ImageList, ImageListItem, ImageListItemBar} from "@material-ui/core";
import React, {useState} from "react";
import {useSelector} from "react-redux";

const starIcons = [
    {
        id: 1,
        stateId: "star1",
        color: "black"
    },
    {
        id: 2,
        stateId: "star2",
        color: "black"
    },
    {
        id: 3,
        stateId: "star3",
        color: "black"
    },
    {
        id: 4,
        stateId: "star4",
        color: "black"
    },
    {
        id: 5,
        stateId: "star5",
        color: "black"
    }
]

const ArtistDetails = () => {

    const movie = useSelector(state => state.selectedMovieSlice.movie);

    const [starIconsList, setStarIcons] = useState(starIcons);

    const ratingHandler = (id) => {
        let newStarsIcons = starIcons.map(star => {
            let newStar = star;
            if (star.id <= id) {
                star.color = "yellow"
            } else {
                star.color = "black";
            }
            return newStar;
        });
        setStarIcons(newStarsIcons);
    }

    const artistClickHandler = (url) => {
        window.location = url;
    }

    return (
        movie &&
        <div className="rightDetails">
            <Typography>
                <span className="bold">Rate this movie:</span>
            </Typography>
            {
                starIconsList.map(star =>
                    <StarBorderIcon className={star.color} key={`star${star.id}`}
                                    onClick={() => ratingHandler(star.id)}/>
                )
            }
            <div className="bold marginBottom16 marginTop16">
                <Typography>
                    <span className="bold">Artists:</span>
                </Typography>
            </div>
            <div className="paddingRight">
                <ImageList rowHeight={160} cols={2}>
                    {
                        <ImageListItem className="gridTile" key={movie['_id']}
                                       onClick={() => artistClickHandler(movie['artistURL'])}>
                            <img src={movie['artistURL']} alt={movie["artists"]}/>
                            <ImageListItemBar title={movie["artists"]}/>
                        </ImageListItem>
                    }
                </ImageList>
            </div>
        </div>
    );
}

export default ArtistDetails;

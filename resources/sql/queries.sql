-- name: create-story!
-- creates a new story
INSERT INTO story
(name, description, created_at, preview_photo)
VALUES (:name, :description, :created, :preview)

-- name: get-stories
-- get all stories
SELECT * FROM story

-- name: get-story
-- get single story by id
SELECT * FROM story
WHERE id = :id

-- name: create-element!
-- create new element for story
INSERT INTO element
(story_id, content, position, type)
VALUES (:story, :content, :position, :type)

-- name: update-element!
-- update existing element
UPDATE element
SET content = :content, position = :position
WHERE id = :id

-- name: get-elements-by-story
-- get all elements for story by id
SELECT * FROM element
WHERE story_id = :id

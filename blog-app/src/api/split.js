import request from '@/request'

export function createSplit(split) {
  return request({
    url: '/api/split/create',
    method: 'post',
    data: split
  })
}

export function replySplit(split) {
  return request({
    url: '/api/split/reply',
    method: 'post',
    data: split
  })
}

export function thumbsSplit(id) {
  return request({
    url: `/api/split/thumbs/${id}`,
    method: 'post'
  })
}

export function getSplitlist(pageVo) {
  return request({
    url: '/api/split/list',
    method: 'post',
    data: pageVo
  })
}

